from lib2to3.pgen2.grammar import opmap
import requests
from bs4 import BeautifulSoup
loginurl = ('https://www.freecodecamp.org/')
secure_url = (
    'https://www.freecodecamp.org/fcc647a48bd-ac82-4bba-becd-98bc9159e554')

payload = {
    'nav-link': '/fcc647a48bd-ac82-4bba-becd-98bc9159e554',
}
with requests.session() as s:
    s.post(loginurl, data=payload)
    r = s.get(secure_url)
    soup = BeautifulSoup(r.content, 'html.parser')
    print(soup.prettify())
