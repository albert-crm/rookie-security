生成环境
openssl version
OpenSSL 1.1.1q
1
2
生成PKCS #1私钥
openssl genrsa -out private#1.pem
1
默认生成的PKCS#1

-----BEGIN RSA PRIVATE KEY-----
密钥内容
-----END RSA PRIVATE KEY-----
1
2
3
生成PKCS#8私钥
openssl pkcs8 -topk8 -inform PEM -in private#1.pem -outform PEM -nocrypt -out private#8.pem
1
借助已生成的PKCS #1私钥通过命令生成PKCS #8私钥

-----BEGIN PRIVATE KEY-----
密钥内容
-----END PRIVATE KEY-----

PKCS#8和PKCS#1的明显区别是BEGIN和END中的RSA标识去掉了，对比内容，也不太一样，想要看具体的结构区别，可以通过ASN1在线解析工具，把密钥粘贴进去，通过解码可以看出来两者结构有明显区别。

生成PKCS#8公钥
openssl  rsa -in private#1.pem -pubout -out public#8.pem
1
内容如下

-----BEGIN PUBLIC KEY-----
密钥内容
-----END PUBLIC KEY-----

细心的朋友可能发现，生成公钥是通过PKCS #1私钥生成的，这样得到的结果会是PKCS#8公钥吗，通过生成的结果可以看到，得到的确实是是PKCS#8公钥(因为没有RSA标识)。但是和通过PKCS #8私钥生成的结果一致吗？结果是一致的，如果不相信，可以试试看。

openssl  rsa -in private#8.pem -pubout -out public#8_2.pem
1
如果想要PKCS #1的公钥可以吗，当然可以
通过PKCS#8公钥生成PKCS#1公钥
openssl rsa -pubin -in public#8.pem -RSAPublicKey_out -out public#1.pem
1
如果生成的公钥默认是PKCS#1，可以生成PKCS#8的公钥吗，当然可以

通过PKCS#1公钥生成PKCS#8公钥
openssl rsa -in public#1.pem -pubout -RSAPublicKey_in -out public#8.pem
1
总结
OpenSSL 1.1.1q （应该是1.1.x版本都是，但是未测试其他）默认生成的PKCS#1私钥，如果需要PKCS#8私钥需要转换，默认生成的是PKCS#8公钥，如果需要PKCS#1公钥，需要转换。
————————————————
版权声明：本文为CSDN博主「风神幻龙」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/a7442358/article/details/127888512

