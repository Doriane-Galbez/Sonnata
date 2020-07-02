%Banque de sons des notes
A = 2  ;  %AMPLITUDE DU SIGNAL
fs = [0:0.000125:1.0]; %ECHANTILLONAGE

%A4

freq =220;
signal = notePiano(1,freq);
note = filtreFlute(signal,10000);
%note2 = filtrePiano(note,10000);
%note = filtrePiano(signal,10000);
%note3=filtreFlute(note2,10000);
soundsc(note)
audiowrite('son_modif_avec_filtre_ppt.wav',note,8000);