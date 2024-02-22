1）、定义
MAC是含有密钥散列函数算法，包含了MD和SHA的特性，并在此基础上加入了密钥，通常也会把MAC成为HMAC（keyed-Hash Message Authentication Code）。MAC算法集合了MD和SHA两大系列消息摘要算法，MD系列有HmacMD2、HmacMD4、HmacMD5，SHA系列有HmacSHA1、HmacSHA224、HmacSHA256、HmacSHA384、HmacSHA512。
经MAC算法得到的摘要值可以使用十六进制编码表示，其摘要值长度与参与实现的算法摘要值长度相同，比如HmacSHA1算法得到的摘要长度就是SHA1算法得到摘要长度，都是160位二进制数，换算成十六进制编码为40位。

2）、应用场景
在MD和SHA均不满足使用场景要求的时候，MAC是一个有效的补充。
————————————————

                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY 版权协议，转载请附上原文出处链接和本声明。

原文链接：https://blog.csdn.net/chenwewi520feng/article/details/131302314
