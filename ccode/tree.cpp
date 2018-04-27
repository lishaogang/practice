#include"cstdio"
#include"cstring"
#define lowbit(i) i&(-i)
using namespace std;
const int MAXN=1000105;//以H的范围确定  
typedef long long LL;
int bit[MAXN];
void add(int i,int x)
{
    while(i<=MAXN)
    {
        bit[i]+=x;
        i+=lowbit(i);
    }
}
int sum(int i)
{
    int ans=0;
    while(i>0)
    {
        ans+=bit[i];
        i-=lowbit(i);
    }
    return ans;
}
int n;
int h[MAXN];
int t[MAXN];
LL s[MAXN];
long long ans;
int main()
{
    for(int i = 0; i < 10; i++)
        printf("%d\t",bit[i]);
    scanf("%d",&n);
    for(int i=0;i<n;i++)
    {
        scanf("%d",&h[i]);
        h[i]++;            //注意:测试数据存在身高为0的小朋友 
        t[i]=i-sum(h[i]);
        add(h[i],1);
        s[i+1]=s[i]+(i+1);    
    }
    memset(bit,0,sizeof(bit));
    for(int i=n-1;i>=0;i--)
    {
        t[i]+=sum(h[i]);
        add(h[i]+1,1);    //注意加1 这样可去掉相同身高 
        ans+=s[t[i]];
    }
    printf("%lld\n",ans);
    return 0;
}