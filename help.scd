a simple pattern to  concatenate in one event a bunch of events, like ppar, but returning the resulting chord and its duration (until an event change it)

e.g.
Pbind( \deg, 0,1,2,3 ,  \dur, 1,2,1,2...)
Pbind( \deg, 3,2,1,0 ,  \dur, 2,1,2,1...)

results as
=>
( degree: [0,3], dur: 1)
( degree: [1,3], dur: 1)
( degree: [1,2], dur: 1)
( degree: [2,2], dur: 1)

It's a kind of analysis pattern


a=	Pbind(\degree, Pseq([0,1,2,3]), \dur, 1);
b=	Pbind(\degree, Pseq([3,2,1,0]), \dur, Pseq([2,1], inf));
PPoly(a, b).asStream.nextN(20, ())
