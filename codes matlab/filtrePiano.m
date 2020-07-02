function filtrer = filtrePiano(melodie,Fe)

R = 300 ;
C = 0.1*10^2;
b0 = 1;
b1 = 0;
a0 = 1+2*R*C*Fe;
a1 = 1-2*R*C*Fe;

filtrer = filter([b0,b1],[a0,a1],melodie);

%C = 0.1*10^200;
%R = 300  ;