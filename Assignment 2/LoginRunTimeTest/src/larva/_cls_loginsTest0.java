package larva;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_loginsTest0 implements _callable{

public static PrintWriter pw; 
public static _cls_loginsTest0 root;

public static LinkedHashMap<_cls_loginsTest0,_cls_loginsTest0> _cls_loginsTest0_instances = new LinkedHashMap<_cls_loginsTest0,_cls_loginsTest0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\balza\\workspace\\LoginRunTimeTest/src/output_loginsTest.txt");

root = new _cls_loginsTest0();
_cls_loginsTest0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_loginsTest0 parent; //to remain null - this class does not have a parent!
public static boolean inLogin;
public static boolean inAlert;
int no_automata = 1;
 public int invalidLogins =0 ;
 public boolean alertScr =false ;
 public boolean loginScr =false ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_loginsTest0() {
}

public void initialisation() {
}

public static _cls_loginsTest0 _get_cls_loginsTest0_inst() { synchronized(_cls_loginsTest0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_loginsTest0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_loginsTest0_instances){
_performLogic_validLoginsProp(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_loginsTest0[] a = new _cls_loginsTest0[1];
synchronized(_cls_loginsTest0_instances){
a = _cls_loginsTest0_instances.keySet().toArray(a);}
for (_cls_loginsTest0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_loginsTest0_instances){
_cls_loginsTest0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_validLoginsProp = 3;

public void _performLogic_validLoginsProp(String _info, int... _event) {

_cls_loginsTest0.pw.println("[validLoginsProp]AUTOMATON::> validLoginsProp("+") STATE::>"+ _string_validLoginsProp(_state_id_validLoginsProp, 0));
_cls_loginsTest0.pw.flush();

if (0==1){}
else if (_state_id_validLoginsProp==3){
		if (1==0){}
		else if ((_occurredEvent(_event,8/*invalidLogin*/)) && (invalidLogins <2 )){
		invalidLogins ++;
_cls_loginsTest0.pw .println ("Invalid Login event. Invalid logins: "+invalidLogins );

		_state_id_validLoginsProp = 3;//moving to state LOGGEDOUT
		_goto_validLoginsProp(_info);
		}
		else if ((_occurredEvent(_event,12/*inLoginScreen*/)) && (inLogin ==true )){
		_cls_loginsTest0.pw .println ("Invalid Login. Back in login screen, try again!");

		_state_id_validLoginsProp = 3;//moving to state LOGGEDOUT
		_goto_validLoginsProp(_info);
		}
		else if ((_occurredEvent(_event,10/*validLogin*/))){
		invalidLogins =0 ;
_cls_loginsTest0.pw .println ("Valid Login event. Invalid logins: "+invalidLogins );

		_state_id_validLoginsProp = 2;//moving to state LOGGEDIN
		_goto_validLoginsProp(_info);
		}
}
else if (_state_id_validLoginsProp==2){
		if (1==0){}
		else if ((_occurredEvent(_event,14/*inAlertScreen*/)) && (inAlert ==true )){
		_cls_loginsTest0.pw .println ("Valid Login. In Alert Screen!");

		_state_id_validLoginsProp = 2;//moving to state LOGGEDIN
		_goto_validLoginsProp(_info);
		}
}
}

public void _goto_validLoginsProp(String _info){
_cls_loginsTest0.pw.println("[validLoginsProp]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_validLoginsProp(_state_id_validLoginsProp, 1));
_cls_loginsTest0.pw.flush();
}

public String _string_validLoginsProp(int _state_id, int _mode){
switch(_state_id){
case 3: if (_mode == 0) return "LOGGEDOUT"; else return "LOGGEDOUT";
case 2: if (_mode == 0) return "LOGGEDIN"; else return "LOGGEDIN";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}