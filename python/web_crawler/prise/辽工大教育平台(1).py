from selenium import webdriver
from selenium.webdriver.common.by import By
import ddddocr
from PIL import Image
import getpass

url = 'https://webvpn.lntu.edu.cn/http/77726476706e69737468656265737421a1a70fcd767e3a033059da/ca/login?service=https%3A%2F%2Fwebvpn.lntu.edu.cn%2Flogin%3Fcas_login%3Dtrue'

driver = webdriver.Edge()
driver.maximize_window()
driver.get(url)

# 定位元素
username = driver.find_element(By.XPATH, '//*[@id="un"]')  # 学号
password = driver.find_element(By.XPATH, '//*[@id="pd"]')  # 密码
img_code = driver.find_element(By.XPATH, '//*[@id="loginForm"]/div/div/div[2]/div[3]/a/img')  # 验证码图片
code_input = driver.find_element(By.XPATH, '//*[@id="code"]')  # 验证码输入框
button = driver.find_element(By.XPATH, '//*[@id="index_login_btn"]')  # 登陆按钮
# 输入元素
username.send_keys('xxxxxxx')   # 这里输入学号
password.send_keys('xxxxxx')  # 这里输入密码

# 验证码图片保存本地
img_code.screenshot('code.png')

# 识别验证码
ocr = ddddocr.DdddOcr()
with open('code.png', 'rb') as fin:
    image = fin.read()

result = ocr.classification(image)

# 输入验证码
code_input.send_keys(result)

# 登陆
button.click()

