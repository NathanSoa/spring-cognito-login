package com.organizamoney.backend.springmonolith.domain.shared.useCases;

import com.organizamoney.backend.springmonolith.domain.shared.exceptions.DomainException;
import com.organizamoney.backend.springmonolith.domain.shared.logic.Either;

public interface UseCase<IN, OUT> {
    Either<DomainException, OUT> execute(IN input);
}
