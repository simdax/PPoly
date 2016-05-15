
PPoly : Pattern{
	var patterns;
	*new{arg ... streams;
		^super.newCopyArgs(streams.collect(_.asStream))
	}
	embedInStream{	
		arg inval=();
		var min, copy;
		var a=(degrees: [], deltas:[]);
		patterns.collect({|x| x.next(inval) })
		.do({|x|
			a.degrees=a.degrees.add(x.degree) ;
			a.deltas=a.deltas.add(x.dur) });
		loop{
			min=a.deltas.minItem;
			a.deltas.collectInPlace{arg x, i;
				if(x.isNil){nil}{
				x=x-min; if(x==0)
				{
					var niouEv;
					niouEv=patterns[i].next(inval);
					a.degrees[i]=try{niouEv.degree}{nil};
					try{niouEv.dur}{nil}
				}
					{x}
				}
			};
			if(a.degrees.reject(_.isNil).isEmpty){^nil};
			(degree:a.degrees.copy, dur:min).yield
		}
	}
}

/*

PPoly(
	Pmel(4), Pmel(5)
).iter.nextN(20)


*/