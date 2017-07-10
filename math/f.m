function output = f()
syms x
x = -pi:0.1:pi
y = 3*x + sin(x)

plot(x,y,'r');
output = diff(y)