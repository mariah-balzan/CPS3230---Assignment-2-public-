package aspects;

import larva.*;
public aspect _asp_loginsTest0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_loginsTest0.initialize();
}
}
before ( boolean inAlert) : (call(* *.setInAlertScreen(..)) && args(inAlert) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_loginsTest0.lock){

_cls_loginsTest0 _cls_inst = _cls_loginsTest0._get_cls_loginsTest0_inst();
_cls_inst.inAlert = inAlert;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 14/*inAlertScreen*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 14/*inAlertScreen*/);
}
}
before () : (call(* *.validLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_loginsTest0.lock){

_cls_loginsTest0 _cls_inst = _cls_loginsTest0._get_cls_loginsTest0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 10/*validLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 10/*validLogin*/);
}
}
before () : (call(* *.invalidLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_loginsTest0.lock){

_cls_loginsTest0 _cls_inst = _cls_loginsTest0._get_cls_loginsTest0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 8/*invalidLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 8/*invalidLogin*/);
}
}
before ( boolean inLogin) : (call(* *.setInLoginScreen(..)) && args(inLogin) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_loginsTest0.lock){

_cls_loginsTest0 _cls_inst = _cls_loginsTest0._get_cls_loginsTest0_inst();
_cls_inst.inLogin = inLogin;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 12/*inLoginScreen*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 12/*inLoginScreen*/);
}
}
}