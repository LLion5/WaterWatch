/*PH��¼����***
**PHģ�齫���ݷ��͵�͸��ģ�飬�����ݷ��ͳ�ȥ�����ݸ�ʽ��16����
	ʾ�������紫�������ڷ�����F7 10 04 01 3E 00 5B 4F 54

�û�ֻҪ��ȡ���������е� 16�����¶�����01 3E�� 16����PH����00 5B

���¶�T=013EH/10=318/10=31.8��PHֵH=005BH/10=91/10=9.1***/

/*���ߣ�����ģ�飺M0-p2.1,M1-p2.2  ,AUX-p1.7,TXD���գ�RXD��p3.1
		��������rx���գ�TX��p3.0
	
*/

#include <reg52.h>
#include <stdio.h>
#include <math.h>
#define uchar unsigned char
#define uint unsigned int

char PH[9] ="123456789";
int yz=12;//��ֵ
long t_ph=0;//phֵ
sbit FMQ = P2^3; //������
sbit M0 = P2^1;
sbit M1 = P2^2;
sbit AUX = P1^7;
sbit led_yz = P1^0;//��ֵ�����
int j=0;


void delay (uint time)   //��ʱ
{
	uint i;
	uint j;
	for (i=0; i<time; i++)
		for (j=0; j<110; j++);
}

void inital (void)
{
	SM0 = 0;
	SM1 = 1;//������ʽ1
	//SMOD = 0;//�����ʲ��ӱ�(Ĭ��)
	REN = 1;//�������
	TMOD = 0X20;//T1�����ڷ�ʽ2���Զ���װ��ֵ
	TH1 = 0XFD;
	TL1 = 0XFD;	//�˳�ֵ������Ϊ��9600bps
	TR1 = 1;	//��ʱ����������Ϊ�����ʵķ�����
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
		while (TI == 0);//�ȴ����ͽ���
		TI = 0;
	}
	if(PH[6] < 50){
		//RI = 1;
		FMQ = 0;
		//SBUF = PH[6];
		//while (TI == 0);//�ȴ����ͽ���
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
		PH[i] = SBUF;//���ܶ�*/
	}
}


void main ()
{	
	M0 =1;
	M1 =0;
	inital ();
	read();
	delay(5);
	Sent ();//��������
 	delay(6000);
	

}
