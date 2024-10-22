package com.picpay.entity.enums;

public enum Perfil {

    CLIENTE(1, "Cliente"), LOJISTA(2, "Lojista");

    private Perfil(Integer id, String desc){
        this.id = id;
        this.desc = desc;
    }

    private Integer id;
    private String desc;

    public Integer getId(){
        return id;
    }

    public String getDesc(){
        return desc;
    }

    public static boolean exists(Integer id){
        for (Perfil x : Perfil.values()) {
            if(x.getId().equals(id)){
                return true;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }


    public static Perfil toEnum(Integer id){
        if(id == null){
            return null;
        }
        for (Perfil x : Perfil.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

}
