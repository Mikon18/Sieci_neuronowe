fileID = fopen('slowa.txt','r');
X = fscanf(fileID,'%1d',[64 Inf])
fileID = fopen('slowa_zmodyfikowane.txt','r');
X2 = fscanf(fileID,'%1d',[64 Inf])
fileID = fopen('indeksy_bin.txt','r');
Y = fscanf(fileID,'%1d',[11 Inf]);
net = newff( minmax(X), [128 11], {'satlins' 'logsig'}, 'trainlm');
net.trainParam.epochs = 1000;
net = train(net,X,Y);
Y2 = net(X2);
Y2 = round(Y2);
yd = bin2dec(char(Y'+'0'))';
ye = bin2dec(char(Y2'+'0'))';
c = 0;
for i=1:100
    c = c + (abs(yd(i) - ye(i)));
end
c/100