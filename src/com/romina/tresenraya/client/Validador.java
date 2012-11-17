package com.romina.tresenraya.client;

import java.util.Vector;


import com.google.gwt.user.client.ui.TextBox;




public class Validador {
	

	
	public Vector< Vector<String> > JugadasGanadorasX;
	public Vector< Vector<String> > JugadasGanadorasY;
	
	public void llenarJugadas(){
		
		this.JugadasGanadorasX = new Vector< Vector<String> > ();
		this.JugadasGanadorasY = new Vector< Vector<String> > ();
		
		Vector<String> j1=new Vector<String>();
		j1.add("00");j1.add("01");j1.add("02");
		
		Vector<String> j2=new Vector<String>();
		j2.add("10");j2.add("11");j2.add("12");
		
		Vector<String> j3=new Vector<String>();
		j3.add("20");j3.add("21");j3.add("22");
		
		Vector<String> j4=new Vector<String>();
		j4.add("00");j4.add("10");j4.add("20");
		
		Vector<String> j5=new Vector<String>();
		j5.add("01");j5.add("11");j5.add("21");
		
		Vector<String> j6=new Vector<String>();
		j6.add("02");j6.add("12");j6.add("22");
		
		Vector<String> j7=new Vector<String>();
		j7.add("00");j7.add("11");j7.add("22");
		
		Vector<String> j8=new Vector<String>();
		j8.add("02");j8.add("11");j8.add("20");
		
		this.JugadasGanadorasX.add(j1);
		this.JugadasGanadorasX.add(j2);
		this.JugadasGanadorasX.add(j3);
		this.JugadasGanadorasX.add(j4);
		this.JugadasGanadorasX.add(j5);
		this.JugadasGanadorasX.add(j6);
		this.JugadasGanadorasX.add(j7);
		this.JugadasGanadorasX.add(j8);
		
		this.JugadasGanadorasY.add(j1);
		this.JugadasGanadorasY.add(j2);
		this.JugadasGanadorasY.add(j3);
		this.JugadasGanadorasY.add(j4);
		this.JugadasGanadorasY.add(j5);
		this.JugadasGanadorasY.add(j6);
		this.JugadasGanadorasY.add(j7);
		this.JugadasGanadorasY.add(j8);
		
		
		
	}
	
	public boolean validarCaracter(Vector<TextBox> v){
		for(int i=0;i < v.size();i++){
			String texto =v.get(i).getText();
			
			if( texto != "X" && texto != "O" && texto =="" ){
				return false;
			}
		}
		return true;
		
	}
	
	public boolean verificarJugadaGanadora(Vector<TextBox> v){
		
		for(int i=0;i<v.size();i++){
			String letra=v.get(i).getText();
			if(letra == "X"){
				for(int j=0;j<JugadasGanadorasX.size();j++){
					String aux=v.get(i).getStyleName().toString();
					aux=aux.substring(12,14);
					JugadasGanadorasX.get(j).remove(aux);
					if(JugadasGanadorasX.get(j).size()==0)
						return true;
				}
			}
			else{
				for(int j=0;j<JugadasGanadorasY.size();j++){
					String aux=v.get(i).getStyleName().toString();
					aux=aux.substring(12,14);
					JugadasGanadorasY.get(j).remove(aux);
					if(JugadasGanadorasY.get(j).size()==0)
						return true;
				}
			}
		}
		
		
		return false;
		
	}
}
