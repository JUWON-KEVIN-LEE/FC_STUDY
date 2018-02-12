# Thread
Study about Thread

ThreadLocal
------------

![사진1](https://github.com/JUWON-KEVIN-LEE/Thread/blob/master/img/thread_local.png)
<pre><code>
    ThreadLocal<UserInfo> local = new ThreadLocal<UserInfo>(); // 현재 쓰레드와 관련된 로컬 변수를 하나 생성한다.
    
    local.set(currentUser); // 로컬 변수에 값 할당

    UserInfo userInfo = local.get(); // 이후 실행되는 코드는 쓰레드 로컬 변수 값을 사용
</code></pre>

<pre><code>
    public class Context {
        public static ThreadLocal<Date> local = new ThreadLocal<Date>();
    }

    class A {
        public void a() {
            Context.local.set(new Date());
        
            B b = new B();
            b.b();

            Context.local.remove();
        }
    }

    class B {
        public void b() {
            Date date = Context.local.get();

            C c = new C();
            c.c();
        }
    }

    class C {
        public void c() {
            Date date = Context.local.get();
        }
    }
</code></pre>
![사진2](https://github.com/JUWON-KEVIN-LEE/Thread/blob/master/img/thread_local1.png)

[출처 : 자바캔(Java Can Do IT)](http://javacan.tistory.com/entry/ThreadLocalUsage)

Handler
--------------
![사진3](https://github.com/JUWON-KEVIN-LEE/Thread/blob/master/img/handler.png)

Looper
--------------
메인 스레드는 Looper가 기본적으로 생성돼 있지만, 새로 생성한 스레드는 기본적으로 Looper를 가지고 있지 않다. <br>
따라서 기본 스레드에서 메시지를 전달받으려면 prepare() 메서드를 통해 Looper를 생성하고, loop() 메서드를 통해 <br>
Looper가 무한히 루프를 돌며 Message Queue에 쌓인 Message나 Runnable 객체를 꺼내 Handler에 전달하도록 해야 한다.

![사진4](https://github.com/JUWON-KEVIN-LEE/Thread/blob/master/img/looper.png)
#### Looper.prepare() 는 현재 Thread 의 ThreadLocal 에서 Map[key : ThreadLocal, value : Looper] 
#### 형태로 새로 생성해서 저장해놓거나 이미 있다면 꺼내서 사용할 수 있도록 되어 있다.

이렇게 활성화된 Looper는 quit()이나 quitSafely() 메서드로 중단할 수 있다. <br>
quit() 메서드가 호출되면 Looper는 즉시 종료되고, <br>
quitSafely() 메서드가 호출되면 현재 Message Queue에 쌓인 메시지들을 처리한 후 종료된다.

