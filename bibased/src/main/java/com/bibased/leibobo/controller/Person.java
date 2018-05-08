package com.bibased.leibobo.controller;
/**
 * 1.每个类都有一个默认的构造器，后面称构造函数，这个默认的没有任何参数的，在代码中看不到的，但是真实存在的，下面我写出来了
 * 2.自己可以多个带参数的构造函数，如果写了有参数的构造函数，那这个默认的没有参数的构造函数就会自动消失，如果需要就必须写出来
 */
public class Person {

	//一个Integer(这个是int类型的封装，int是基本类型，Integer是一个对象，表示的是一样的)类型的属性age
	private Integer age;

	//一ge字符串类型的属性name
	private String name;

	//这个就是默认的构造函数，不写的时候是默认存在的
	//如果有了含参构造函数，这个无参构造函数系统就没有默认的了，要用的话，就要这样显现的写出来
	public Person(){

	}

	//这是一个含有一个参数的构造函数
	public Person(Integer inputAge){
		this.age = inputAge;
	}
	//这是一个含有两个参数的构造函数
	public Person(Integer inputAge,String inputName){
		this.age = inputAge;
		this.name = inputName;
	}


	//无参的构造函数这样用
	Person wucangouzaohanshu = new Person();//前面的Person表示类型的，以后会学到
	//有参数的构造函数这样用
	Person yigecanshu = new Person(22);
	Person laigngecanshu = new Person(22,"erfan");
}
