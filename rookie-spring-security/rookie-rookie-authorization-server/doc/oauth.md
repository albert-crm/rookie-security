### 授权码模式

在浏览器上进行登陆

http://localhost:18900/authorization-server/oauth/authorize?client_id=client-id-rookie&client_secret=client-secret-rookie&response_type=code&scope=access

![5e481e1dd2e3ae7a53689351c4fc490](pic/oauth/5e481e1dd2e3ae7a53689351c4fc490.png)



登陆成功并选择授权，返回如下

http://localhost:18900/authorization-server/access?code=6ILttk

![251ccf542f15477c3ee2274dbbc8608](pic/oauth/251ccf542f15477c3ee2274dbbc8608.png)

获取token

http://localhost:18900/authorization-server/oauth/token?client_id=client-id-rookie&client_secret=client-secret-rookie&scope=access&code=6ILttk&grant_type=authorization_code

![0af2dddc72517c4c82fe0db46739f2f](pic/oauth/0af2dddc72517c4c82fe0db46739f2f.png)

根据token调用请求进行资源访问

![0316950265cb78a7aa25b0444a0ff8a](pic/oauth/0316950265cb78a7aa25b0444a0ff8a.png)