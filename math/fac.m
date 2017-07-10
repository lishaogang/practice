function output1= fac(n)

output1 = 1;
ret = 4
for i = 1:n,
    output1 = output1 * i;
end
if ~exist('save','dir')
    mkdir save;
end
save D:\code\math\save\s1.mat