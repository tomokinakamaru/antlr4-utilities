grammar Math;

prog: expr EOF ;

expr: NUM (ADD NUM)* ;

ADD: '+' ;

NUM: [1-9][0-9]* | [0] ;

WS: [ \t\r\n\u000C]+ -> channel(HIDDEN) ;
