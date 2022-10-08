import threading

import requests
import re
import json
import os
from moviepy.editor import *
from concurrent.futures import ThreadPoolExecutor

lock_project = threading.RLock()

url = input('请输入B站视频url：')

headers = {
    "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36"
}

resp = requests.get(url, headers=headers)
# print(resp.text)

title_re = re.compile('<title data-vue-meta="true">(?P<title>.*?)</title>')
title = title_re.search(resp.text).group('title')
print(title)

play_info_re = re.compile(r"window.__playinfo__=(?P<play_info>.*?)</script>")
play_info = play_info_re.search(resp.text).group("play_info")

dic = json.loads(play_info)

video_url = dic['data']['dash']["video"][0]['baseUrl']
audio_url = dic['data']['dash']["audio"][0]['baseUrl']

# print(video_url)
# print(audio_url)

headers['Referer'] = url  # 对上referer


# 下载视频和音频
def download():
    lock_project.acquire()
    print('正在下载视频')
    v_resp = requests.get(video_url, headers=headers)
    with open("video.mp4", mode="wb") as f:
        f.write(v_resp.content)

    print('正在下载音频')
    a_resp = requests.get(audio_url, headers=headers)
    with open("audio.mp3", mode="wb") as f:
        f.write(a_resp.content)
    lock_project.release()


# 合并起来
def all_():
    lock_project.acquire()
    print('正在进行合并')
    ad = AudioFileClip('audio.mp3')
    vd = VideoFileClip('video.mp4')

    vd2 = vd.set_audio(ad)  # 将提取到的音频和视频文件进行合成
    vd2.write_videofile(f'{title}.mp4')  # 输出新的视频文件
    lock_project.release()


pool = ThreadPoolExecutor(500)
pool.submit(download)
pool.submit(all_)
