package com.huayu.ssm.bo;

/**
 * @author 旗木卡卡西
 * @date 2020/8/11 8:47
 */
public class Person extends Parent {
    @Override
    public void  sysHello(){
        System.out.println("hello哈哈");
    }

    public static void main(String[] args){
        Parent parent=new Parent();
        parent.sysHello();
    }
}
