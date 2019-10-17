grammar Arith;

prog: var* eval EOF ;

var: 'var' NAME '=' NUM ';' ;

eval: 'eval' expr ';' ;

expr: term ((ADD | SUB) expr)? ;

term: fact ((MUL | DIV) term)? ;

fact: SUB? elem ;

elem: NUM | NAME | '(' expr ')' ;

ADD: '+' ;

SUB: '-' ;

MUL: '*' ;

DIV: '/' ;

NAME: [a-z] ;

NUM: [1-9][0-9]* | [0] ;

WS: [ \t\r\n\u000C]+ -> channel(HIDDEN) ;
