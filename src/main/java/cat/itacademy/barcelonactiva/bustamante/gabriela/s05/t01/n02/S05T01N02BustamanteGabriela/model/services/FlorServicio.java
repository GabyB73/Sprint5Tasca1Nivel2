package cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.model.services;

import cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.model.domain.Flor;
import cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.model.dto.FlorDTO;

import java.util.List;
import java.util.Optional;

public interface FlorServicio {

    Flor crearFlor(Flor flor);
    //Flor addFlor(Flor flor);
    List<FlorDTO> getAllFlor();
    Flor updateFlor(Integer pk_FlorID, String nombreFlor, String paisFlor);
    Optional<Flor> getFlorById(int id);
    void deleteFlorById(int id);
    List<FlorDTO> convertToDtoList(List<Flor> flores);
    FlorDTO convertToDto(Flor nuevaFlor);

}
