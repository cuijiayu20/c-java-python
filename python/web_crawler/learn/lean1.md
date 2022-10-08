# 一、web请求
1. **服务器渲染**：可以直接在页面源代码内看到数据。服务器直接将数据和html结合起来。
2. **客户端渲染**：页面源代码里面没有数据。第一次请求只要html骨架，第二次请求拿到数据，在客户端拼接才行。***获取数据方法***：使用浏览器抓包工具**network**
# 二、http协议
## 1.请求
>1. 请求行-->请求方式 *（get/post）* 请求url地址 协议反 爬内容
>2. 请求头-->放一些服务器需要使用的信息
>3. q
>4. 请求体-->放一些请求参数
## 2.响应
>1. 状态行-->协议 状态码
>2. 响应头-->放一些客户端要使用的附加信息
>3.  q
>4.  响应体-->服务器返回的真正客户端福要用的内容（html json）等
## 3.需要学习的内容
### （1）.请求头
>1. User-Agent：请求载体的身份标识（用啥发送的请求）
>2. Referer：防盗链（这次请求是从哪个页面来的？反爬用到）
>3. cookie：本地字符串数据信息（用户登录信息，反爬的token）
### （2）.响应头
>1. cookie:本地字符串数据信息（用户登录信息，反爬的token）
>2. 各种神奇的莫名其妙的字符串（这个需要经验，一般是token字样，防止各种攻击和反爬）
### （3）请求方式
>1. GET:获取内容。显示提交。
>2. POST: 修改数据，上传，隐式提交。
### （4）筛选
- Fetch/XHR是第二次响应的包。
- 链接中问号前面是地址，后面是参数带不带问号结果一样。
- 封装参数，将问好以及后面的内容作为一个字典。字典内部装的是Pyload下的内容（其就是将参数写成了漂亮格式）
### 参数变化
- 向下一拉刷新出新的页面start会增加
# 三、requests
## 1.用到的代码及功能
- from urllib.request import urlopen
-  resp=urlopen(url2)#打开网址
-  resp.read()#.read读取
-  .decode("utf-8")#解码，将字节转化为字符串
-  requests.get(url)请求 
-  requests.post(url,data=dat)post请求
-  resp=requests.get(url=url,params=param)GET请求
-  resp.request.url 获取响应地址
-  resp.request.headers获取默认“User-Agent”
-  resp.close()记得关闭，不然访问次数过多报错。
## 2.伪装浏览器
建立一个字典，保存请求头的User-Agent内容,使用requests.get(url，headers=)请求 
```python
import requests
dict = {
    "User-Agent": 内容
}
resp=requests.get(url，headers=dict)
```
## 3.post请求和get请求
- GET:requests.get(url)请求，搜索参数直接在url中
- POST:requests.post(url,data=dat)post请求,搜索参数在post函数内，使用字典保存参数，使用data传参。
  
# 四、正则表达
## 1.常用元字符
- "."匹配除换行符以外的任意字符
- "\w"匹配字母数字下划线
- "\s"匹配任意的空白符
- "\d"匹配数字
- ”\n“匹配一个换行符
- "\t"匹配制表符号
- "^"匹配字符串的开始
- "$"匹配字符串的结尾
- "\W"匹配非数字字母或下划线
- "\D"匹配非数字
- ”\S“匹配非空白符
- "a|b"匹配a或者b
- "()"匹配括号内的表达式，也表示一个组
- "[a-z]"匹配字符组中的字符
- "[^...]"匹配非字符组的内容
## 2.量词(控制前面东西出现的次数)
- “*”表示重复零次或更多次
- “+” 表示重复一次或更多次
- "?" 表示重复零次或一次
- "{n}" 表示重复n次
- "{n,}"表示重复N次或更多次
- “{n,m}” 表示重复n次到m次
## 3.贪婪匹配和惰性匹配
- ".*" 贪婪匹配 ，尽可能多的匹配内容
- “.*?” 惰性匹配，尽可能少的出现结果
# 五、re模块
## 1.一些代码
- lst =re.findall(r"正则表达式","字符串") 匹配正则内容返回list类型，低效率
- list2=re.finditer(r"正则表达式","字符串")返回迭代器，效率高
- s= re.search(r"正则表达式","字符串")返回迭代器要加group()但只要找到一个结果就返回
- s= re.match(r"正则表达式","字符串")从头开始匹配，如果首部不是需要的字符，则显示错误
- obj=re.compile(r"正则表达式",re.S) 预加载正则表达式，可以多次重复使用。re.S表示让“.”可以匹配换行字符，正则表达式多,长时，可以另起一行再写
- ret=obj.finditer("字符串")
- 该代码不会将\<div class>等部分包括进去，只会包含(?P< wahaha>.\*?)内的东西 含义是将第二个”.\*?“的东西传输保存到wahaha里面，直接输出wahaha对应的东西。(?P<分组名字>正则) 可以单独从正则匹配的内容中进一步提取内容。
  ```python
  obj= re.compile(r"<div class>='.*?'<span id='\d*'>(?P<wahaha>.*?)</span></dive>)\",re.S)

  result =obj.finditer("字符串")
  print(result.group("wahaha"))
  # 该代码不会将 <div class> 等部分包括进去，只会包含(?P<wahaha>.*?)内的东西 含义是将第二个”.*?“的东西传输保存到wahaha里面，直接输出wahaha对应的东西。
  ```
# 六、小功能
## 1.python应用
1. 将query中的内容插入（适合于get不适合与post）
```python
url=f"https://www.baidu.com/s?wd={query}"
```
2. 字典中需要引号，以及需要用逗号分割
 ```python
  param{
    "type": "11",
    "interval_id":" 10:0",
    "action": "",
    "start": "0",
    "limit": "20"
}
   ```
3. 在字符串前面加r,里面特殊字符不需要转义
```python
lst =re.findall(r"正则表达式","字符串")
```
4. 迭代器的使用案例
```python
list2=re.finditer(r"玩儿.*?游戏","玩儿吃鸡游戏，晚上一起上游戏，干嘛呢？打游戏")
for i in list2:
    print(i.group()) #必须加group()才能输出字符串
```
5. 去除前面的空格
```python
 print(it.group("year").strip())
```