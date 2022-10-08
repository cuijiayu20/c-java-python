import requests
from lxml import etree


def get_path(che_ci):
    url = f"https://trains.ctrip.com/TrainSchedule/{che_ci}"
    response = requests.get(url)
    response_tree = etree.HTML(response.text)
    station_name = response_tree.xpath(
        '//*[@id="ctl00_MainContentPlaceHolder_pnlResult"]/div[2]/table[2]/tbody/tr/td[3]/text()')
    return station_name
