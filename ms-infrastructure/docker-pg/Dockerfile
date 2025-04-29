FROM postgres:14.1-alpine

# Copy the custom pg_hba.conf into the container
COPY ./pg_hba.conf /var/lib/postgresql/data/pg_hba.conf

# Set environment variables
#ENV POSTGRES_USER=postgres
#    POSTGRES_PASSWORD=postgres
#    POSTGRES_DB=keycloak

# Expose the PostgreSQL port
EXPOSE 5432

# Set the command to run when the container starts
CMD ["postgres"]