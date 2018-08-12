* @Mapper
* 分布式session
请求头，参数，cookie携带token，兼容APP和PC浏览器端
每次访问延长cookie有效期

* config
@Configuration


第四章
* 为什么秒杀还有建秒杀商品表，秒杀订单，不直接用商品表和订单表
因为促销形式多样，今天秒杀，明天可能1元购，商品表不能一直增加字段。因为新建秒杀表
* VO DTO
VO:视图对象
DTO：数据传输对象
* 数据库存价格一般存整数，不会存小数
* @{${goods.goodsImg}} @代表resources/static目录
* 数据库ID尽量别用自增，容易被人循环，用uuid被人笑话，可以参考snowflake算法，twitter出品

* 在自己Service不提倡引入别的dao，一般引入别的service
* 获取插入的ID 
@SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")


第五章
*JMeter压测
QPS