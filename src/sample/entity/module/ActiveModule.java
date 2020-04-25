package sample.entity.module;

public class ActiveModule {

    public String moduleName;

    private ActiveModule() { }

    private static ActiveModule activeModule = new ActiveModule();

    public static ActiveModule getActiveModule() {
        return activeModule;
    }

    public static void setActiveModuleFields(String moduleName){
        activeModule.moduleName = moduleName;
    }}
