package br.ufms.controle_venda.services;

import br.ufms.controle_venda.dtos.VendaDto;
import br.ufms.controle_venda.models.Venda;
import br.ufms.controle_venda.repositories.VendaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class VendaService {
    private final VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }
    @Transactional
    public void novaVenda(Venda venda){
        vendaRepository.save(venda);
    }

    @Transactional
    public Page<Venda> findAll(Pageable page){
        return vendaRepository.findAll(page);
    }

    public Optional<Venda> findById(Long id) {
        return vendaRepository.findById(id);
    }
    @Transactional
    public Venda atualizar(Long id, VendaDto vendaDto) {
        Optional<Venda> venda = this.vendaRepository.findById(id);
        venda.ifPresent(atualizandoVenda -> {
            BeanUtils.copyProperties(vendaDto, atualizandoVenda);
        });
        return venda.get();
    }
    public Optional<Venda> deletar(Long id) {
        Optional<Venda> venda = this.vendaRepository.findById(id);
        venda.ifPresent(vendaRepository::delete);
        return venda;
    }


}
