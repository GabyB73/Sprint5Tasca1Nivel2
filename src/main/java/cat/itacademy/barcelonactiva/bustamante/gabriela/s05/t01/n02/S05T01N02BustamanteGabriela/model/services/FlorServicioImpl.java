package cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.model.services;

import cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.exceptions.FlorNotFoundException;
import cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.model.domain.Flor;
import cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.model.repository.FlorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class FlorServicioImpl implements FlorServicio{
    @Autowired
    private final FlorRepositorio florRepositorio;

    public FlorServicioImpl(FlorRepositorio florRepositorio) {

        this.florRepositorio = florRepositorio;
    }
    //Métodos CRUD
    @Override
    public Flor crearFlor(Flor flor) {

        return florRepositorio.save(flor);
    }

    @Override
    public List<FlorDTO> getAllFlor() {
        List<Flor> flores = florRepositorio.findAll();
        return convertToDtoList(flores);
    }

    @Override
    public Flor updateFlor(Integer pk_FlorID, String nuevoNombreFlor, String nuevoPaisFlor) {
        Optional<Flor> florOptional = florRepositorio.findById(pk_FlorID);

        if (florOptional.isPresent()) {
            Flor flor = florOptional.get();
            flor.setNombreFlor(nuevoNombreFlor);
            flor.setPaisFlor(nuevoPaisFlor);
            return florRepositorio.save(flor);
        } else {
            throw new FlorNotFoundException("Flor no encontrada con el ID: " + pk_FlorID);
        }
    }

    @Override
    public Optional<Flor> getFlorById(int id) {

        return florRepositorio.findById(id);
    }

    @Override
    public void deleteFlorById(int id) {
        florRepositorio.deleteById(id);
    }
    //Métodos de conversión
    //Método para convertir el DTO en la Entidad
    @Override
    public List<FlorDTO> convertToDtoList(List<Flor> flores) {
        return flores.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //Método para convertir la Entidad en DTO
    @Override
    public FlorDTO convertToDto(Flor nuevaFlor) {
        FlorDTO florDTO = new FlorDTO();
        florDTO.setPk_FlorID(nuevaFlor.getPk_FlorID());
        florDTO.setNombreFlor(nuevaFlor.getNombreFlor());
        florDTO.setPaisFlor(nuevaFlor.getPaisFlor());

        // Lógica para determinar el tipo de sucursal basado en la lista de países de la UE
        if (FlorDTO.getPaisesUE().contains(nuevaFlor.getPaisFlor())) {
            florDTO.setTipoFlor("UE");
        } else {
            florDTO.setTipoFlor("Fuera UE");
        }

        return florDTO;
    }
}
