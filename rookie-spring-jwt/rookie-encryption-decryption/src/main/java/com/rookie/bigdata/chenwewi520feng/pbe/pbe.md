5、PBE
1）、介绍
PBE（Password Based Encryption）基于密码加密算法是一种基于密码的加密算法，其特点是密码（password）由用户自己保管，采用随机数（加盐）杂凑多重加密等方法保证数据的安全性。PBE没有密钥，其密码（password）代替了密钥，为了增加密码的安全性，采用了加盐的处理方式。PBE是对称加密算法的综合性算法，常见的算法有PBEWithMD5AndDES，该算法是使用了MD5和DES构建PBE算法。

2）、应用场景
PBE算法通常用于需要用户输入口令的场合，例如保护用户数据。

3）、示例
以下是jdk实现示例
————————————————

                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY 版权协议，转载请附上原文出处链接和本声明。

原文链接：https://blog.csdn.net/chenwewi520feng/article/details/131306068
