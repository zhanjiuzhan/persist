websocket链接

socket
	1. ws://localhost:9092/websocket/shower 链接
	2. 链接成功 onOpen  发送登录消息
	    {"type": 1, "content": "{\"username\": \"dw_chenglei\", \"token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJvcmcuYWNjb3VudC5jbCIsInN1YiI6ImR3X2NoZW5nbGVpIiwiaWF0IjoxNjA1NTI2NDMyLCJleHAiOjE2MDU1MjgyMzJ9.MHmHojhgOOZAnnaJw9gPsv2k-zoW3FHCYU_kzp0pVyoOggF4boowr-C9Sy5gUqUs788dJYueNPVMHMwZ3_Y5Iw\"}"}
        base64编码
        eyJ0eXBlIjogMSwgImNvbnRlbnQiOiAie1widXNlcm5hbWVcIjogXCJkd19jaGVuZ2xlaVwiLCBcInRva2VuXCI6IFwiZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6VXhNaUo5LmV5SnBjM01pT2lKdmNtY3VZV05qYjNWdWRDNWpiQ0lzSW5OMVlpSTZJbVIzWDJOb1pXNW5iR1ZwSWl3aWFXRjBJam94TmpBMU5USTJORE15TENKbGVIQWlPakUyTURVMU1qZ3lNeko5Lk1IbUhvamhnT09aQW5uYUp3OWdQc3Yyay16b1czRkhDWVVfa3pwMHBWeW9PZ2dGNGJvb3dyLUM5U3k1Z1VxVXM3ODhkSll1ZU5QVk1ITXdaM19ZNUl3XCJ9In0=

		反馈信息:{"content":"200","type":1}

	3. {"content":"{\"heart\":1,\"latitude\":12.442,\"longitude\":54.452,\"userId\":\"dw_chenglei\"}","type":0}
	eyJjb250ZW50Ijoie1wiaGVhcnRcIjoxLFwibGF0aXR1ZGVcIjoxMi40NDIsXCJsb25naXR1ZGVcIjo1NC40NTIsXCJ1c2VySWRcIjpcImR3X2NoZW5nbGVpXCJ9IiwidHlwZSI6MH0=

    反馈 {"content":"2","type":0}

    4. 关闭链接两种方法
        session没有关闭使用
        > 发送信息 {"type": 2, "content":"cc11c38623394c919cc618f4f6e177ea"}
        session异常关闭使用
        > 调用链接 /shower/user/socket/close.do {"relationId": "cc11c38623394c919cc618f4f6e177ea"}

发布信息
user
	POST: /shower/user/publish.do  {"message": "hello"}
