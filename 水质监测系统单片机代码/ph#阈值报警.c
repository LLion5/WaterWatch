/*PH烧录程序***
**PH模块将数据发送到透传模块，将数据发送出去。数据格式：16进制
	示例：例如传感器串口发出：F7 10 04 01 3E 00 5B 4F 54

用户只要截取串口数据中的 16进制温度数据01 3E和 16进制PH数据00 5B

则温度T=013EH/10=318/10=31.8℃PH值H=005BH/10=91/10=9.1***/

/*连线，传输模块：M0-p2.1,M1-p2.2  ,AUX-p1.7,TXD悬空，RXD接p3.1
		传感器：rx悬空，TX接p3.0
	
*/

#include <reg52.h>
#include <stdio.h>
#include <math.h>
#define uchar unsigned char
#define uint unsigned int

char PH[9] ="123456789";
int yz=12;//阈值
long t_ph=0;//ph值
sbit FMQ = P2^3; //蜂鸣器
sbit M0 = P2^1;
sbit M1 = P2^2;
sbit AUX = P1^7;
sbit led_yz = P1^0;//阈值警告灯
int j=0;


void delay (uint time)   //延时
{
	uint i;
	uint j;
	for (i=0; i<time; i++)
		for (j=0; j<110; j++);
}

void inital (void)
{
	SM0 = 0;
	SM1 = 1;//工作方式1
	//SMOD = 0;//波特率不加倍(默认)
	REN = 1;//允许接受
	TMOD = 0X20;//T1工作于方式2：自动重装初值
	TH1 = 0XFD;
	TL1 = 0XFD;	//此初值波特率为：9600bps
	TR1 = 1;	//定时器是用来作为波特率的发生器
	EA = 1;
	ES = 1;	
}


void Sent(void)
{
	int i;
	RI = 1;
	for (i=0;i<9;i++)
	{
		SBUF = PH[i];
		while (TI == 0);//等待发送结束
		TI = 0;
	}
	if(PH[6] < 50){
		//RI = 1;
		FMQ = 0;
		//SBUF = PH[6];
		//while (TI == 0);//等待发送结束
		delay(1000);
		FMQ = 1;
	}else{
		FMQ = 1;
	}
}

void Read(void)
{
	uchar i;
	TI = 1;
	for (i=0;i<9;i++)
	{
		while (RI == 0);//
		RI = 0;
		PH[i] = SBUF;//接受端*/
	}
}


void main ()
{	
	M0 =1;
	M1 =0;
	inital ();
	read();
	delay(5);
	Sent ();//发送数据
 	delay(6000);
	

}
