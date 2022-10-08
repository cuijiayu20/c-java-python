from Browser_operation.Get_browser_driven import Browser_Instantiation
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from time import sleep
import pyautogui

driver = Browser_Instantiation()

url = "https://kyfw.12306.cn/otn/resources/login.html"

driver.get(url)


print("进入登陆界面")

sleep(0.5)

# 解决特征识别
script = 'Object.defineProperty(navigator, "webdriver", {get: () => false,});'
driver.execute_script(script)

username = driver.find_element(By.XPATH, '//*[@id="J-userName"]').send_keys(input("请输入账号: "))

print("请在新出现的界面输入密码")
print("！！！注意：输入密码时，请切换英文输入法，否则有概率输入不上！！！")
sleep(1)
key = pyautogui.password("User Password")
driver.find_element(By.XPATH, '//*[@id="J-password"]').send_keys(key)
sleep(0.2)
driver.find_element(By.XPATH, '//*[@id="J-login"]').click()
sleep(5)

hua_dong = driver.find_element(By.XPATH, '//*[@id="nc_1_n1z"]')

ActionChains(driver).drag_and_drop_by_offset(hua_dong, 500, 0).perform()

sleep(2)

# window = driver.window_handles
# driver.switch_to.window(window[-1])
# sleep(5)
# driver.find_element(By.XPATH, '//*[@id="pop_166513021969868116"]/div[2]/div[3]/a').click()

cookie = driver.get_cookies()
cookiea = [item["name"] + "=" + item["value"] for item in cookie]
cookiestr = '; '.join(item for item in cookiea)
# print(cookiestr)
driver.close()

print("登陆完毕")

print("-" * 60)
