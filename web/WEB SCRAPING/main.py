from gettext import find
from os import link
from bs4 import BeautifulSoup
import requests
html_text = requests.get(
    'https://indianexpress.com/article/cities/delhi/actor-nora-fatehi-questioned-7-hours-extortion-case-sukesh-chandrashekhar-8128249/').text
soup = BeautifulSoup(html_text, 'lxml')
content = soup.find(class_='native_story_title').text
print(f"content : {content.strip()}")
# https://ums.lpu.in/lpuums/StudentDashboard.aspx

# from lib2to3.pgen2.grammar import opmap
# import requests
# from bs4 import BeautifulSoup
# loginurl = ('https://ums.lpu.in/lpuums/LoginNew.aspx')
# secure_url = ('https://ums.lpu.in/lpuums/default3.aspx')
# payload = {
#     'txtU': '12103196',
# }
# payload1 = {
#     'TxtpwdAutoId_8767': 'Meena@121'
# }
# payload2 = {
#     'ddlStartWith': 'default3.aspx'
# }
# with requests.session() as s:
#     s.post(loginurl, data=payload)
#     s.post(loginurl, data=payload1)
#     s.post(loginurl, data=payload2)
#     r = s.get(secure_url)
#     soup = BeautifulSoup(r.content, 'html.parser')
#     print(soup.prettify())
