# from gettext import find
# from os import link
# from bs4 import BeautifulSoup
# from more_itertools import only
# import requests

# html_text = requests.get('https://ums.lpu.in/lpuums/LoginNew.aspx').text
# soup = BeautifulSoup(html_text, 'lxml')
# content = soup.find(class_='sign_in').text
# print(f"Covid cases : {content.strip()}")
# https://ums.lpu.in/lpuums/StudentDashboard.aspx

from lib2to3.pgen2.grammar import opmap
import requests
from bs4 import BeautifulSoup

loginurl = ('https://ums.lpu.in/lpuums/LoginNew.aspx')
secure_url = ('https://ums.lpu.in/lpuums/StudentDashboard.aspx')

payload = {
    'txtU': '12103196',
    'TxtpwdAutoId_8767': 'Meena@121'
}

with requests.session() as s:
    s.post(loginurl, data=payload)
    r= s.get(secure_url)
    soup=BeautifulSoup(r.content, 'html.parser')
    print(soup.prettify())
    