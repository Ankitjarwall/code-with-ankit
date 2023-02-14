const { Builder, By, Key, until } = require('selenium-webdriver')
const assert = require('assert')

describe('lpu', function () {
  this.timeout(30000)
  let driver
  let vars
  beforeEach(async function () {
    driver = await new Builder().forBrowser('chrome').build()
    vars = {}
  })
  afterEach(async function () {
    await driver.quit();
  })
  it('lpu', async function () {
    await driver.get("https://ums.lpu.in/lpuums/LoginNew.aspx")
    await driver.manage().window().setRect({ width: 1936, height: 1048 })
    await driver.findElement(By.id("txtU")).click()
    await driver.findElement(By.id("txtU")).sendKeys("12103196")
    await driver.findElement(By.id("TxtpwdAutoId_8767")).click()
    await driver.findElement(By.id("TxtpwdAutoId_8767")).sendKeys("Meena@9549")
    await driver.findElement(By.id("iBtnLogins")).click()
    {
      const element = await driver.findElement(By.css(".active:nth-child(4) img"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    {
      const element = await driver.findElement(By.CSS_SELECTOR, "body")
      await driver.actions({ bridge: true }).moveToElement(element, 0, 0).perform()
    }
    await driver.findElement(By.css("#regno > b")).click()
    assert(await driver.findElement(By.css("#regno > b")).getText() == "Reg. No.: 12103196 | Section: D2101")
  })
})
