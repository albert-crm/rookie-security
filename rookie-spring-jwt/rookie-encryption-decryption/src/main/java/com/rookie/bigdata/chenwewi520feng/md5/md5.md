任何消息经过散列函数处理后都会获得唯一的散列值（hashcode），该过程称为消息摘要，其散列值成为数字指纹，其算法即是消息摘要算法。

消息摘要算法又称为散列算法，其核心在于散列函数的单向性，即通过散列函数可获得对应的散列值，但不可通过该散列值获得其原始信息。

消息摘要算法包含三大系列，即MD 、SHA 和MC，常用于验证数据的完整性，是数字签名的核心算法。

MD，message digest，消息摘要算法，包括MD2、MD4、MD5
SHA，secure hash algorithm，安全散列算法，包括SHA-224、SHA-256、SHA-384、SHA-512
MAC，message authentication code，消息认证码算法，综合了MD和SHA算法，包括HmacMD5、HmacSHA1、HmacSHA256、HmacSHA384、HmacSHA512
————————————————

                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY 版权协议，转载请附上原文出处链接和本声明。

原文链接：https://blog.csdn.net/chenwewi520feng/article/details/131302314


、MD
1）、介绍
MD5是由MD2、MD3、MD4改进而来，是典型的消息摘要算法。MD5算法对输入任意长度的消息进行运行，产生一个128位的消息摘要。如果将这个128位的信息摘要信息换算成十六进制，则可以得到一个32位的字符串(32位的数字字母混合码)。

2）、应用场景
MD5之后的推荐替代应该是SHA，已经不适合安全性要求较高的场景。
————————————————

                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY 版权协议，转载请附上原文出处链接和本声明。

原文链接：https://blog.csdn.net/chenwewi520feng/article/details/131302314
