import java.util.*;
class Student {
    private String id, name;
    private int marks;
    public Student(String id, String name, int marks){
        this.id=id; this.name=name; this.marks=marks;
    }
    public String getId(){ return id;}
    public int getMarks(){return marks;}
    public String getRole(){return "Undergrad";}

    @Override
    public String toString(){
        return id+" - "+name+ " ("+marks+": "+getMarks()+")";
    }
}
class GraduateStudent extends Student{
    private String area;
    public GraduateStudent(String id, String name, int marks, String area){
        super(id, name, marks);
        this.area=area;
    }
    public String getRole(){
        return "Grad ("+area+")";
    }
}
class HonorStudent extends Student{
    private int bonus;
    public HonorStudent (String id, String name, int marks, int bonus){
        super(id, name, marks+bonus);
    }
}

class Repository<T>{
    private Map<String,T> data=new HashMap<>();
    public void save(String id, T obj){data.put(id,obj);}
    public T find(String id){return data.get(id);}
    public void delete(String id){data.remove(id);}
}

public class wwcMain{
    void getTopper(List<Student> l) {
    Iterator<Student> it = l.iterator();
    int ma = Integer.MIN_VALUE;
    while (it.hasNext()) {
        Student ss = it.next();
        ma = Math.max(ma, ss.getMarks());
    }
    System.out.println("Topper marks: " + ma);
}


    public static void main(String []args){
        List<Student> list=new ArrayList<>();
        list.add(new Student("s1", "John", 99));
        list.add(new Student("s2", "don", 79));
        list.add(new Student("s3", "kon", 89));
        list.add(new Student("s4", "mon", 39));

        Repository<Student> repo= new Repository<>();
        for(Student s: list)repo.save(s.getId(),s);
        System.out.println("All: ");
        list.forEach(System.out::println);
        

        System.out.println("\nLookup S2:");
        Student s=repo.find("s2");
        System.out.println(s!= null ? s: "not found");

        Iterator< Student> it=list.iterator();
        while(it.hasNext()){
            Student st=it.next();
            if(st.getmarks()<70){
                it.remove();
                repo.delete(st.getId());
            }
        }
        System.out.println("\nAFTER removal: ");
        list.forEach(System.out::println);
    }

}
