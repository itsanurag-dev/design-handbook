package dev.anurag.handbook.patterns.creational.singleton;

public class SingletonDemo {
    public static void main(String[] args) {
        EagerInitializedSingleton eager = EagerInitializedSingleton.getInstance();
        BillPughSingleton billPugh = BillPughSingleton.getInstance();
        DoublyCheckedSingleton doubleChecked = DoublyCheckedSingleton.getInstance();
        FinalStaticSingleton finalStatic = FinalStaticSingleton.INSTANCE;
        EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
        ClonableSingleton cloneSingleton = ClonableSingleton.getInstance();
        SerializedSingleton serializedSingleton = SerializedSingleton.getInstance();
        StaticBlockSingleton staticBlock = StaticBlockSingleton.getInstance();

        System.out.println("EagerInitializedSingleton: " + eager.hashCode());
        System.out.println("BillPughSingleton: " + billPugh.hashCode());
        System.out.println("DoublyCheckedSingleton: " + doubleChecked.hashCode());
        System.out.println("FinalStaticSingleton: " + finalStatic.hashCode());
        System.out.println("EnumSingleton: " + enumSingleton.hashCode());
        System.out.println("ClonableSingleton: " + cloneSingleton.hashCode());
        System.out.println("SerializedSingleton: " + serializedSingleton.hashCode());
        System.out.println("StaticBlockSingleton: " + staticBlock.hashCode());
    }
}
