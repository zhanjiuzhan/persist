insert into message_header values (1, 1, 0 ,0, 113.3317, 23.156019, now()) on duplicate key update createTime = values(createTime);
insert into help_message_content values (
    1,
    '技术人员测试信息',
    '2020/09/30 技术人员进行第一条信息的添加, 用于测试项目启动的数据库初始化',
    '数据库创建测试详细...巴拉巴拉...此处省略10000字',
    '["https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601463509499&di=48ffe1785c74259eaea6a1695850191d&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F4e4a20a4462309f7dc937133750e0cf3d7cad617.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601463586120&di=f7f30e56d83cefb6724cec58fc286fff&imgtype=0&src=http%3A%2F%2Fpic36.nipic.com%2F20131227%2F13419492_164834171147_2.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601463612204&di=898a434ba6dc03abf4afd7aa661a35cd&imgtype=0&src=http%3A%2F%2Fpic35.photophoto.cn%2F20150429%2F0020033531641870_b.jpg"]')
    on duplicate key update title = values(title);
