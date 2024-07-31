'use client';

import { handleAddToCart, revalidateGetCartByUser } from '@/utils/actions/actions';
import { useAppSelector } from '@/utils/redux/hook';
import { getCartSelector } from '@/utils/redux/slice/cartSlice';
import { Image, message, Pagination } from 'antd';
import { useRouter } from 'next/navigation';

interface IProps {
    meta: IMeta | undefined;
    products: IProduct[] | undefined;
}
function HomepageUser({ meta, products }: IProps) {
    // const cartCurrent = useAppSelector(getCartSelector);

    // console.log('cartCurrent', cartCurrent);

    const router = useRouter();

    const handleOnChangePage = (page: number, pageSize: number) => {
        router.replace(`/?page=${page}&size=${pageSize}`, {
            scroll: false,
        });
    };

    const handleClickAddToCart = async (idProduct: string) => {
        const result = await handleAddToCart(idProduct);

        if (result.data) {
            message.success('Add product to cart successful');
            await revalidateGetCartByUser();
        } else {
            message.error(result.message);
        }
    };

    return (
        <div className="tab-className text-center pt-3">
            <div className="row g-4">
                <div className="col-lg-4 text-start">
                    <h1>Products</h1>
                </div>
                <div className="col-lg-8 text-end">
                    <ul className="nav nav-pills d-inline-flex text-center mb-5">
                        <li className="nav-item">
                            <a
                                className="d-flex m-2 py-2 bg-light rounded-pill active"
                                data-bs-toggle="pill"
                                href="#tab-1"
                            >
                                <span className="text-dark" style={{ width: '130px' }}>
                                    All Products
                                </span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div className="tab-content">
                <div id="tab-1" className="tab-pane fade show p-0 active">
                    <div className="row g-4">
                        <div className="col-lg-12">
                            <div className="row g-4 mb-3">
                                {products?.map((item) => (
                                    <div className="col-md-6 col-lg-4 col-xl-3">
                                        <div className="rounded position-relative fruite-item">
                                            <div className="fruite-img">
                                                <Image
                                                    src={
                                                        process.env
                                                            .NEXT_PUBLIC_BACKEND_URL +
                                                        '/storage/products/' +
                                                        item.image
                                                    }
                                                    className="img-fluid w-100 rounded-top"
                                                    alt=""
                                                    fallback="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAMAAzAMBIgACEQEDEQH/xAAbAAEAAwEBAQEAAAAAAAAAAAAAAQUGBAMCB//EADYQAAICAQIDBgMGBQUAAAAAAAABAgMEESEFMUESEyIyUXEjUmEUQoGx0eEVM2KRwTSCofDx/8QAFgEBAQEAAAAAAAAAAAAAAAAAAAEC/8QAFhEBAQEAAAAAAAAAAAAAAAAAABEB/9oADAMBAAIRAxEAPwD9lAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJIJAgAAAAAAAAAAAScmZn1Ym0vFP5IvcDq66dT4svqq2sthD6OSRQZPEsm/btKuD+7DY4/zLErS/wAQxFt38WfUM3Fm9I5Fevo5aMzA16CFa5NNap7AylVtlMtabJQ9uTLPF4w/JlLRfNFfmhCrgEQnGyCnW04+qJIoAAAAAAAASQSBAAAAAAAABJBz5+T9lx3Ned7R9wObifEO40qpfxfvS+Uo2223JuTfV8w5OTbeurerb6kFQABUAAAJTIAV04ObZhzbjvCT8UH1RoqbYX1qyp6xZlDv4VmOi7upv4U+no/UgvwARQAAAAAJIJAgAAAAAAADfoUHGb+9y3CL8FS0S+vUv29E36LUyc5uc5SfOTbLg+QAVkAAAAAAAAAAGm4de8jEhN+ZLSXujoW5U8As1V1b5LSRbGWgAAAAAJIJAgAAAAAAAHxf/Js0+VmUXJexrZrtQlH1TRk2mm0+cXoXE1AAKgAAAAAAAAAALPgX+ps9OwvzLwpuAR8d1j5JJFwZaAAAAAAkgkCAAAAAAAADN8TqdObYtNpeJGkODi+M78dTgtZ17+6AoAAaQAAQAAAAABsDowMZ5ORGCXhW8n9BVXXCKu6xI685+JnYNElolokDKgAAAAASQSBAAAAAAAAAAAo+K8P7qUr6F8J+ZfL+xWmu6aaJ+5V5nCO1rZibb71v/BUUoPqyE65diyMoS9JLQ+SoABbvQAB103OvE4ffkyWkexDrOW3/AKNVz01WXWKuqOsmaPCxYYlPYjvKXml6k4mJViQca1q3zk+bPcyoAAAAAAAASQSBAAAAAAAAB82WQrj2rJKK9XscGbxSumTro0smtm+iZTX3W3zcrpObX9kBoKuIYttihG3xN6LVaanV7p/iZH2/v6Gh4XmLJr7ub+NFb/X6liOuyELY9myEZL6o458Jxp7xUo+zO7oCKqnwWvXa+a/2o+48FpXmusfskiyAHPTg41OjhUm11ludOrIDlGMXKckopavUDzvurorc7JdlI86M7Gvl2YWaS9HsUnEct5duq/lx8q/ycm5YNd1Bn8Tid2P4bNbK30b3Rd419WTV26p6pc/Ve4g9QAQAAAJIJAgAAABrpuAlKMdXJ6JLVvoij4jxJ2/Cx21Vycusv2I4nnu6cqaXpUuf9TK4sAAFiD3JjOUJxnCTjJcmiABd4fFoTXZydIT6SXJ/oWcWpx1g4yXqnqZHmfULJwesJyj7MQrWD8NTN/xDLS0+0TPieZk2LSd82vfQkK0GTl0Y0dbZrX5Vuykzs+eZ4dO7qW/Z9fc4wIU6gAoHpRdZRYrKpdmXX+r6M8wBpMHNhlx0WkLI+aH6HUZOuydVinW9JLkaTAy4ZdPa2U154roTR0AAihJBIEAAB/wVfGczsJ41T8T3m/lXp7nfl3rGolbLmuS9WZeUnOcpyerbbZcRAAKAACAAAAAAAAAAAAAAAAB64uRPFujZWtfVfMjyBNVq6bY3Vxsg/DLl+h9lHwbK7ux0Tfw7N19JfuXhFCSCQIAPPIuVFFlj+6tfx6AU3GsjvL1VHy18/fqVxMm223zlzINIAAIAAAAAAAAAAAAAAAAAAAAAqU2t0afCv+048Zrm/N7/APdzLlpwK/s3Tp+7Nax90SFXRJBJFQVnHbezRCrrN6v2RZlBxqzt5vZ6VxS/EuI4AAVAAAAAAAAAAAAAAAAAAAAAAAAA9Me102wtXOMk2eYA1yacNY8nyJOXh1jtwqm+emj/AAOoy0//2Q=="
                                                />
                                            </div>
                                            <div
                                                className="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                style={{ top: '10px', left: '10px' }}
                                            >
                                                {item.name}
                                            </div>
                                            <div className="p-4 border border-secondary border-top-0 rounded-bottom">
                                                <h5>
                                                    <span>{item.name}</span>
                                                </h5>

                                                <p>{item.shortDesc}</p>

                                                <div className="d-flex justify-content-center flex-lg-wrap flex-column">
                                                    <p className="text-dark fs-5 fw-bold mb-4">
                                                        {item.price
                                                            // @ts-ignore
                                                            .toFixed(0)
                                                            .replace(
                                                                /(\d)(?=(\d{3})+(?!\d))/g,
                                                                '$1.',
                                                            )}
                                                        đ
                                                    </p>

                                                    <button
                                                        className="btn border border-secondary rounded-pill px-3 text-primary"
                                                        onClick={() =>
                                                            handleClickAddToCart(
                                                                item.id ?? '',
                                                            )
                                                        }
                                                    >
                                                        <i className="fa fa-shopping-bag me-2 text-primary"></i>{' '}
                                                        Add to cart
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                ))}
                            </div>

                            <nav aria-label="Page navigation example ">
                                <ul className=" justify-content-center">
                                    <Pagination
                                        defaultCurrent={1}
                                        total={meta?.total}
                                        current={meta?.page}
                                        pageSize={meta?.pageSize}
                                        onChange={(page, number) =>
                                            handleOnChangePage(page, number)
                                        }
                                    />
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default HomepageUser;
