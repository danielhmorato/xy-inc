##PROJETO SIMPLES DE WEB SERVICES - XYINC

* Aplicação desenvolvida no Eclipse Neon 4.6.1 utilizando o JDK 8u112
* Webservices feito com o Jersey 2.25
* Banco de dados simples utilizando h2
* Servidor de aplicação: Apache Tomcat 8.5.9

###Serviços:

#####1. Listar todos os pontos de referencia:     
>	**Metodo**: `GET`     
>	**Caminho**: `/xyinc/geo/local/listar`    	
>	**Headers**:  
>	  
>		accept:*/*  
>		content-type:application/json
		
#####2. Adicionar um novo ponto de referencia:
>	**Metodo**: `POST`    
>	**Caminho**: `/xyinc/geo/local`   	
>	**Headers**: 
>	
>		accept:*/*
>		content-type:application/json   		
>	**Data**:   
>	
>		MIME Type: application/x-www-form-urlencoded    
>		parametros: 
>			nomePOI - Nome do ponto de referencia   
>			pontoX - Coordenada X   
>			pontoY - Coordenada Y
				
#####3. Listar todos os pontos de referência por proximidade até uma distância:  
>	**Metodo**: `POST`    	
>	**Caminho**: `/xyinc/geo/local`   	
>	**Headers**:
>	    
>		accept:*/*  
>		content-type:application/json   		
>	**Data**: 
>	    
>		MIME Type: application/x-www-form-urlencoded    
>		parametros:  
>		    dmax - Distância máxima  
>			pontoX - Coordenada X de referência  
>			pontoY - Coordenada Y de referência
			


####