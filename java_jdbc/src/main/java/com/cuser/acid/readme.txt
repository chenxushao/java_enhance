事务(ACID)
●原子性(atomicity):组成事务处理的语句形成一个逻辑单元，不能只执行其中的一部分。
●一致性(consistency):在事务处理执行前后，数据库是一致的(数据库数据完整性约束)。
●隔离性(isolcation):一个事务处理对另一个事务处理的影响。
●持续性(durability):事务处理的效果能够被永久保存下来。

connection.setAutoCommit(false);//打开事务
connection.commit();//提交事务
connection.rollback();//回滚事务