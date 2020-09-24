websocket链接

user
用户注册
	登录成功的话会创建一个关系 关系的状态为待链接 并返回relationId 60秒后这个关系就过期了
	1. POST: /shower/user/register.do  {"username": "chenglei"}
	response: {"data":{"createTime":"2020-09-24 10:17:32","relationId":"cc11c38623394c919cc618f4f6e177ea","status":0,"username":"chenglei"},"msg":"","status":200}

socket
	2. ws://localhost:9092/websocket/shower 链接
	3. 链接成功 onOpen  发送登录消息
	    {"type": 1, "content":"cc11c38623394c919cc618f4f6e177ea"}
		type=1 是登录消息类型
		content是relationId
		反馈信息:{"content":"200","messageType":"LOGIN_MSG","type":1}

	4. {"content":"{\"heart\":1,\"latitude\":12.442,\"longitude\":54.452,\"relationId\":\"cc11c38623394c919cc618f4f6e177ea\"}","type":0}

    5. 关闭链接两种方法
        session没有关闭使用
        > 发送信息 {"type": 2, "content":"cc11c38623394c919cc618f4f6e177ea"}
        session异常关闭使用
        > 调用链接 /shower/user/socket/close.do {"relationId": "cc11c38623394c919cc618f4f6e177ea"}

发布信息
user
	POST: /shower/user/publish.do  {"message": "hello"}
