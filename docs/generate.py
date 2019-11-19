#!/usr/bin/python3

from pprint import pprint
import xml.sax
import glob
import plotly.express as px
import pandas as pd
import os

tag_blacklist = ["Project","Packages","Classes","Metrics", "Methods"]

class Metric:
  def __init__(self, p, attributes):
    self.p = str.join('/', list(filter(lambda x: x not in tag_blacklist, p)))
    self.name = attributes["name"]
    self.description = attributes["description"]
    self.value = float(attributes["value"])

  def to_data(self):
    return [ f"{self.p}:{self.name}", self.value ]


class JasomeFileHandler(xml.sax.ContentHandler):
    def __init__(self):
        self.currentPath = []
        self.currentTag = ""
        self.metrics = []
        self.currentMetric = None

    # Call when an element starts
    def startElement(self, tag, attributes):
        if tag == "Metric":
          self.currentMetric = Metric(self.currentPath, attributes)

        element = tag
        if "name" in attributes:
          element += f"[{attributes['name']}]"
        self.currentPath.append(element)
        self.currentTag = tag

    # Call when an elements ends
    def endElement(self, tag):
        if self.currentTag == "Metric" and self.currentMetric:
            self.metrics.append(self.currentMetric)
            self.currentMetric = None
        self.currentPath.pop()
        self.currentTag = ""

    # Call when a character is read
    def characters(self, content):
      pass



if (__name__ == "__main__"):

    # create an XMLReader
    parser = xml.sax.make_parser()
    # turn off namepsaces
    parser.setFeature(xml.sax.handler.feature_namespaces, 0)

    results = []
    for f in glob.glob(os.path.dirname(__file__) + '/*.xml'):
      f_id = int((f.split('/')[-1]).split('_')[0])
      handler = JasomeFileHandler()
      parser.setContentHandler(handler)
      parser.parse(f)
      for m in handler.metrics:
        results.append([f_id] + m.to_data())

    df = pd.DataFrame(results, columns=["key", "name", "value"]).sort_values(['key'])

    fig = px.line(df, x='key', y='value', color='name')
    fig.show()
