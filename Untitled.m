fileID = fopen('slowa.txt','r');
X = fscanf(fileID,'%d',[8 Inf])
fileID = fopen('slowa_modyfikowane.txt','r');
X2 = fscanf(fileID,'%d',[8 Inf])
fileID = fopen('indeksy.txt','r');
Y = fscanf(fileID,'%d',[1 Inf])
net = newff( minmax(X), [15 1], {'satlin' 'purelin'}, 'trainbr');
net = train(net,X,Y);
y2 = net(X2)