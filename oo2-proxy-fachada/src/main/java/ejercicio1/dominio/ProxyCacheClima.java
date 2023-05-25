package ejercicio1.dominio;

import java.time.LocalDateTime;

public class ProxyCacheClima implements Clima {

	private Clima miClima;
	private String estadoDelDiaCache;
	private LocalDateTime tiempoCache;

	public ProxyCacheClima(Clima miClima) {
		this.miClima = miClima;
		this.tiempoCache = LocalDateTime.now();
	}

	@Override
	public String estadoDelDia() {
		
		if((estadoDelDiaCache == null)||(LocalDateTime.now().isAfter(this.tiempoCache.plusSeconds(1)))) {
			this.estadoDelDiaCache = miClima.estadoDelDia();
		}
		return this.estadoDelDiaCache;
			
	}

}
