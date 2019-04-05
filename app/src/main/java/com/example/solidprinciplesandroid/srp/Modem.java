package com.example.solidprinciplesandroid.srp;

public interface Modem {
	public void dial(String phone);
	public void hangup();
	
	public void send(char ch);
	public char receive();
}
/*
 * This interface got two responsibility, one is connection management 
 * and the other is data communication
 * We can make 2 interfaces here to achieve SRP
*/
interface ModemConnection{
	public void dial(String phone);
	public void hangup();
}

interface ModemDataManagement{
	public void send(char ch);
	public char receive();
}