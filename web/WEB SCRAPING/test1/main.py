
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager

search_term=input("Enter the product : ")

driver = webdriver.Chrome(ChromeDriverManager().install())
driver.get("https://www.flipkart.com/")

try:
    driver.find_element_by_xpath('//button [@class="_2KpZ6l _2doB4z"]').click()
except:
    pass

driver.find_element_by_xpath('//input [@name="q"]').send_keys(search_term)
driver.find_element_by_xpath('//button[@class="L0Z3Pu"]').click()
