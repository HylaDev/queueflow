package com.queueflow.application.service;

import com.queueflow.domain.exception.ServiceDeskAlreadyExistsException;
import com.queueflow.domain.model.ServiceDesk;
import com.queueflow.domain.port.ServiceDeskRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceDeskServiceTest {

    @Mock
    private ServiceDeskRepositoryPort repositoryPort;

    @InjectMocks
    private ServiceDeskService serviceDeskService;

    @Test
    void shouldCreateServiceDeskWhenCodeDoesNotExist() {

        ServiceDesk serviceDesk = new ServiceDesk();
        serviceDesk.setCode("CNI");
        serviceDesk.setName("Carte d'identité");

        when(repositoryPort.existsByCode("CNI"))
                .thenReturn(false);

        when(repositoryPort.save(any(ServiceDesk.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ServiceDesk result =
                serviceDeskService.create(serviceDesk);

        assertNotNull(result);
        assertEquals("CNI", result.getCode());
        assertTrue(result.getActive());
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());

        verify(repositoryPort).save(serviceDesk);
    }

    @Test
    void shouldThrowExceptionWhenCodeAlreadyExists() {

        ServiceDesk serviceDesk = new ServiceDesk();
        serviceDesk.setCode("CNI");

        when(repositoryPort.existsByCode("CNI"))
                .thenReturn(true);

        assertThrows(
                ServiceDeskAlreadyExistsException.class,
                () -> serviceDeskService.create(serviceDesk)
        );

        verify(repositoryPort, never())
                .save(any());
    }

    @Test
    void shouldReturnAllServiceDesksWhenNoFilterIsProvided() {

        when(repositoryPort.findAll())
                .thenReturn(List.of(
                        new ServiceDesk(),
                        new ServiceDesk()
                ));

        List<ServiceDesk> result =
                serviceDeskService.getServiceDesks(null);

        assertEquals(2, result.size());

        verify(repositoryPort).findAll();
    }

    @Test
    void shouldReturnOnlyActiveServiceDesksWhenActiveFilterIsTrue() {

        when(repositoryPort.findAllByActive(true))
                .thenReturn(List.of(
                        new ServiceDesk()
                ));

        List<ServiceDesk> result =
                serviceDeskService.getServiceDesks(true);

        assertEquals(1, result.size());

        verify(repositoryPort)
                .findAllByActive(true);
    }

    @Test
    void shouldReturnOnlyInactiveServiceDesksWhenActiveFilterIsFalse() {

        when(repositoryPort.findAllByActive(false))
                .thenReturn(List.of(
                        new ServiceDesk()
                ));

        List<ServiceDesk> result =
                serviceDeskService.getServiceDesks(false);

        assertEquals(1, result.size());

        verify(repositoryPort)
                .findAllByActive(false);
    }
}