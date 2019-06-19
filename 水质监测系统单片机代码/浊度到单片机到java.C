/*浊度烧录程序***
**将控制命令发送给浊度模块，模块返回电压值，***/
/**透传模块：M0 = P2^1；M1 = P2^2；RXD接浊度TX；TXD悬空；AUX接P1^7
***浊度模块：RX接P31 **/


#include <reg52.h>
#define uchar unsigned char
#define uint unsigned int

sbit M0 = P2^1;
sbit M1 = P2^2;
sbit AUX = P1^7;

char order[6] = "AT+V\r\n"; //电压读取指令


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


void SentOrder(void)  //命令发送
{
	int i;
	RI = 1;
	for (i=0;i<6;i++)
	{
		SBUF = order[i];
		while (TI == 0);//等待发送结束
		TI = 0;
	}
}


void main ()
{		
	inital ();
	M0 = 1;
	M1 = 0; //从机发送模式
	SentOrder ();//发送命令
	delay(6000);//延时6秒 

}